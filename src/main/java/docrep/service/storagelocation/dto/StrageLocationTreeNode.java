package docrep.service.storagelocation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class StrageLocationTreeNode {
    String label;
    String data;
    String expandedIcon = "fa-folder-open";
    String collapsedIcon = "fa-folder";
    List<StrageLocationTreeNode> children;
}
