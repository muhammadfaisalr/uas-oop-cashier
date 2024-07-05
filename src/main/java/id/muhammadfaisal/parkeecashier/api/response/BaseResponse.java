package id.muhammadfaisal.parkeecashier.api.response;

import lombok.Data;

@Data
public class BaseResponse {
    public int code;
    public String message;
    public Object data;
}
