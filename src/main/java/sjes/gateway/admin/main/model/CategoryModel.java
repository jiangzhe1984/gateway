package sjes.gateway.admin.main.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangzhe on 15-12-2.
 */
@Data
public class CategoryModel {
    private String id;

    private String text;

    List<CategoryModel> items = new ArrayList<>();
}
