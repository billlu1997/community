package lu.bill.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOs;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    private Integer totalPage;

    private Integer page;

    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalPage, Integer page) {

        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);

        if(page < 1) {
            page = 1;
        }
        if(page > totalPage) {
            page = totalPage;
        }

        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if(page + i <= totalPage) {
                pages.add(page + i);
            }
        }


        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        if (pages.contains(totalPage)) {
            showLastPage = false;
        } else {
            showLastPage = true;
        }
    }
}
