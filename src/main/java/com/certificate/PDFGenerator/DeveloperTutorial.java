package com.certificate.PDFGenerator;

//package com.internity.certificateGenerator;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "student_info")
public class DeveloperTutorial {

	  @Id
	  @Column(name = "id")
	  private long id;

	  @Column(name = "name")
	  private String name;

	  @Column(name = "college")
	  private String college;

	  @Column(name = "course")
	  private String course;

	  @Column(name = "eventName")
	  private String eventName;
	  
	  @Column(name = "project")
	  private String project;


	  @Column(name = "streamName")
	  private String streamName;
	  
	  @Column(name = "startDate")
	  private String startDate;

	  @Column(name = "endDate")
	  private String endDate;
	  
	  


	  public DeveloperTutorial() {

	  }

	  public DeveloperTutorial(long id, String name, String  college, String course, String  eventName, String project, String  streamName, String startDate, String endDate) {
	    this.id = id;
	    this.name = name;
	    this.college = college;
	    this.course = course;
	    this.eventName = eventName;
	    this.project = project;
	    this.streamName = streamName;
	    this.startDate = startDate;
	    this.endDate = endDate;
	  }

	 
	  public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "DeveloperTutorial [id=" + id + ", name=" + name + ", college=" + college + ", course=" + course
				+ ", eventName=" + eventName + ", project=" + project + ", streamName=" + streamName + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
}

	  