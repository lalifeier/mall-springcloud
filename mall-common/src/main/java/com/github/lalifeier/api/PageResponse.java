package com.github.lalifeier.api;

//import com.github.pagehelper.Page;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResponse<T>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private int errCode;

    private String errMessage;

    private List<T> data;

    private PageInfo pageInfo;

    public static PageResponse success() {
        PageResponse response = new PageResponse();
        response.setSuccess(true);
        return  response;
    }

    public static <T> PageResponse<T> success(List<T> data, int pageNum, int pageSize, int totalCount) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setPageInfo(new PageInfo(pageNum, pageSize, totalCount));
        return response;
    }

//    public static <T> PageResponse<T> success(Page<T> page) {
//        PageResponse<T> response = new PageResponse<>();
//        response.setSuccess(true);
//        response.setData(page.getResult());
//        response.setPageInfo(new PageInfo(page.getPageNum(), page.getPageSize(), page.getTotal()));
//        return response;
//    }

    public static <T> PageResponse<T> success(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setData(page.getContent());
        response.setPageInfo(new PageInfo(page.getNumber() , page.getSize(), page.getTotalElements()));
        return response;
    }

    public static PageResponse failure(int errCode, String errMessage) {
        PageResponse response = new PageResponse();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static PageResponse failure(IError error) {
        PageResponse response = new PageResponse();
        response.setSuccess(false);
        response.setErrCode(error.getCode());
        response.setErrMessage(error.getMessage());
        return response;
    }
}
