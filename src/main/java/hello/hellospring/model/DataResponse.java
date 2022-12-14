package hello.hellospring.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataResponse {
    public List<ProductInfo> data;
}
