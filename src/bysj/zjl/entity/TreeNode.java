package bysj.zjl.entity;
/**
 * easyui tree树 封装表
 * @author zhoujiali
 *
 */

import java.util.List;
import java.util.Map;

public class TreeNode {
	
	private String id="0";
    private String text; // 树节点名称
    private String iconCls; // 前面的小图标样式
    private Boolean checked = false; // 是否勾选状态
    private Map<String, Object> attributes; // 自定义属性
    private List<TreeNode> children; // 子节点
    private String parentId;
    private String state = "open"; // 是否展开（open,closed）
    
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public TreeNode(String id, String text, String iconCls, Boolean checked, Map<String, Object> attributes,
			List<TreeNode> children, String parentId, String state) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.parentId = parentId;
		this.state = state;
	}
	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", iconCls=" + iconCls + ", checked=" + checked
				+ ", attributes=" + attributes + ", children=" + children + ", parentId=" + parentId + ", state="
				+ state + "]";
	}
	public TreeNode() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
