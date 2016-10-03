package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import plalashop.controller.PlalashopController;
import plalashop.utils.Utils;

public class Advertise {
	private Long advertiseId;
	private String advertiseOwner;
	private String fileName;
	private String linkAdvertise;
	private Long queue;
	
	public Advertise(ResultSet resultSet) {
		if(resultSet != null){
			try {
				advertiseId = resultSet.getLong("advertise_id");
				advertiseOwner = resultSet.getString("advertise_owner");
				fileName = resultSet.getString("file_name");
				linkAdvertise = resultSet.getString("link_advertise");
				queue = resultSet.getLong("queue");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Advertise(String advertiseOwner,String linkAdvertise,Long queue) {
		this.advertiseOwner = advertiseOwner;
		this.linkAdvertise = linkAdvertise;
		this.queue = queue;
	}
	public Advertise() {
		
	}
	public Long getAdvertiseId() {
		return advertiseId;
	}
	public void setAdvertiseId(Long advertiseId) {
		this.advertiseId = advertiseId;
	}
	public String getAdvertiseOwner() {
		return advertiseOwner;
	}
	public void setAdvertiseOwner(String advertiseOwner) {
		this.advertiseOwner = advertiseOwner;
	}
	public String getFileName() {
		return Utils.convertImageToBase64(PlalashopController.UPLOAD_DIRECTORY+fileName);
	}
	public String getFileName(int i) {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLinkAdvertise() {
		return linkAdvertise;
	}
	public void setLinkAdvertise(String linkAdvertise) {
		this.linkAdvertise = linkAdvertise;
	}

	public Long getQueue() {
		return queue;
	}

	public void setQueue(Long queue) {
		this.queue = queue;
	}
	
	
}
