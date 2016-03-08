package com.example.ListViewSuppot;

//listView的信息类
public class ListData {
	public String title=null,description=null;
	public int iconId=0;
	public Class classes=null;
	
	public ListData(String title, String description, int iconId,
			Class classes){
		this.title = title;
		this.description = description;
		this.iconId = iconId;
		this.classes = classes;
	}

	public Class getClasses() {
		return classes;
	}

	public void setClasses(Class classes) {
		this.classes = classes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
}
