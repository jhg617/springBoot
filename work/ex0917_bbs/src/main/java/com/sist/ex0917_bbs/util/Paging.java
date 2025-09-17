package com.sist.ex0917_bbs.util;

public class Paging {
    int nowPage = 1;//현재페이지 값 == cPage
    int numPerPage = 10; // 한 페이지당 보여질 게시물 수

    //페이징을 위한 변수
    int totalCount; // 총 게시물의 수

    int pagePerBlock = 5; // 한 블럭당 표현할 페이지 수
    int totalPage; // 총 페이지 수

    int begin;// 현재 페이지 값에 따라 bbs_t테이블에서 가져올 게시물의 시작 행번호
    int end;// 현재 페이지 값에 따라 bbs_t테이블에서 가져올 게시물의 마지막 행번호

    int startPage; // 한 블럭의 시작페이지 값
    int endPage; // 한 블럭의 마지막페이지 값

    private boolean isPrePage; // 이전페이지 기능 가능여부(true: 이전기능 활성화)
    private boolean isNextPage; // 다음페이지 기능 가능여부(true: 다음기능 활성화)

    //JSP에서 표현할 페이징 HTML코드를 저장할 곳!
    private StringBuffer pagingHTML;

    private String bname; // 게시판 이름

    //인자로 현재페이지, 전체게시물 수, 한페이지에 표현할 게시물의 수, 
    //한 블럭당 표현할 페이지 수를 받아서 생성함 그리고
    //JSP에서 표현할 페이징 HTML코드까지 만들고자 한다.
    public Paging(int nowPage, int totalCount, int numPerPage, int pagePerBlock, String bname){
        this.nowPage = nowPage;
        this.totalCount = totalCount;
        this.numPerPage = numPerPage;
        this.pagePerBlock = pagePerBlock;
        this.bname = bname;
        

        //이전 기능과 다음 기능을 초기화 하자!
        isPrePage = false;
        isNextPage = false;

        //총 페이지 수 구하기
        this.totalPage = (int)Math.ceil((double)totalCount/numPerPage);

        //현재페이지 값이 총 페이지 수를 넘지 못하도록 하자!
        if(nowPage > totalPage)
            nowPage = totalPage;

        // 현재 블럭의 시작페이지 값과 마지막페이지 값을 구하자!
        startPage = (int)((nowPage-1)/pagePerBlock)*pagePerBlock+1;
        endPage = startPage + pagePerBlock - 1;

        // 위에서 구한 마지막 페이지가 총 페이지 값을 넘어갈 때가 빈번하게 생긴다.
        if(endPage > totalPage)
            endPage = totalPage;

        // 현재 페이지 값에 의해 시작게시물의 행번호와 마지막 게시물의 행번호를 구하자
        // 예) 현재페이지: 1, begin:1, end: 10
        // 예) 현재페이지: 2, begin:11, end: 20
        // 예) 현재페이지: 3, begin:21, end: 30
        begin = (nowPage-1)*numPerPage+1;
        end = nowPage*numPerPage;

        //이전기능을 활성화 할 것인지 판단하자
        if(startPage > 1)
            isPrePage = true;

        //다음기능을 활성화 할 것인지 판단하자
        if(endPage < totalPage)
            isNextPage = true;

        // 이제 현재페이지 값도 알고, 시작페이지와 마지막페이지 값을 알았으니
        // JSP에서 표현할 페이징 HTML코드를 만들어 보자!
        pagingHTML = new StringBuffer("<ol class='paging'>");

        if(isPrePage){
            pagingHTML.append("<li><a href='list?cPage=");
            pagingHTML.append(startPage-1);
            pagingHTML.append("&bname=");
            pagingHTML.append(bname);
            pagingHTML.append("'> &lt; </a></li>");
        } else {
            pagingHTML.append("<li class='disable'> &lt; </li>");
        }

        // 페이지 번호 출력되는 반복문
        for(int i=startPage; i<=endPage; i++){
            if(i == nowPage){ //현재페이지
                pagingHTML.append("<li class='now'>");
                pagingHTML.append(i);
                pagingHTML.append("</li>");
            } else {
                pagingHTML.append("<li><a href='list?cPage=");
                pagingHTML.append(i);
                pagingHTML.append("&bname=");
                pagingHTML.append(bname);
                pagingHTML.append("'>");
                pagingHTML.append(i);
                pagingHTML.append("</a></li>");
            }
        }// for의 끝

    // 다음 기능 활성화 여부
    if(isNextPage){
            pagingHTML.append("<li><a href='list?cPage=");
            pagingHTML.append(endPage+1);
            pagingHTML.append("&bname=");
            pagingHTML.append(bname);
            pagingHTML.append("'> &gt; </a></li>");
        } else {
            pagingHTML.append("<li class='disable'> &gt; </li>");
        }
        pagingHTML.append("</ol>");
    }

    //생성시 한페이지에 표현할 게시물의 수와 한 블럭당 표현할 페이지 수를 받아서 생성함
    public Paging(int numPerPage, int pagePerBlock){
        this.numPerPage = numPerPage;
        this.pagePerBlock = pagePerBlock;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;

        // 현재페이지 값이 변경되면 표현할 게시물들이 변경되어야 한다.
        // 즉, begin과 end값이 변경되어 DB로부터 다시 게시물들을 가져와야 함!

        // 현재 페이지 값이 총 페이지 수를 넘지 못하도록 하자!
        if(nowPage > totalPage)
            nowPage = totalPage;

        //각 페이지의 시작 행번호(begin)과 마지막 행번호(end)를 구한다.
        // 예) 현재페이지: 1, begin:1, end: 10
        // 예) 현재페이지: 2, begin:11, end: 20
        // 예) 현재페이지: 3, begin:21, end: 30
        begin = (nowPage-1)*numPerPage+1;
        end = nowPage*numPerPage;

        //현재페이지 값에 따라 블럭의 시작페이지(startPage)구하자
        startPage = (int)((nowPage-1)/pagePerBlock)*pagePerBlock+1;

        //한 블럭의 마지막 페이지 값
        endPage = startPage + pagePerBlock - 1;

        // 위에서 구한 마지막 페이지가 총 페이지 값을 넘어갈 때가 빈번하게 생긴다.
        if(endPage > totalPage)
            endPage = totalPage;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    // 총 게시물의 수가 변경될 때 자동으로 총 페이지 수를 구하면 좋을 것 같다.
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        // 총 게시물 수가 변경될 때 총 페이지수를 구하자!
        /*
        this.totalPage = totalCount/numPerPage;
        if(totalCount%numPerPage != 0)
            this.totalPage++;
        */
        this.totalPage = (int)Math.ceil((double)totalCount/numPerPage);
    }

    public int getPagePerBlock() {
        return pagePerBlock;
    }

    public void setPagePerBlock(int pagePerBlock) {
        this.pagePerBlock = pagePerBlock;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getPagingHTML() {
        return pagingHTML.toString();
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
