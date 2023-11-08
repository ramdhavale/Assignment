package com.dms.beans;
/**
 * 
 * @author Ram
 * Class Item
 * Description This class is used to store the items
 */
public class Item {
private int	id;
private String name;
private String description;
private int categoryId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}



}
