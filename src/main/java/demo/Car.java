package demo;

import org.springframework.data.annotation.Id;

public class Car{

	@Id
	private String id;

	private String make;
	private String model;
	private String color;


	public Car(){	}

	public Car(String make, String model, String color){
		this.make=make;
		this.model=model;
		this.color=color;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake(){
		return this.make;
	}

	public String getModel(){
		return this.model;
	}

	public String getColor(){
		return this.color;
	}

	public void setMake(String make){
		this.make=make;
	}

	public void setModel(String model){
		this.model=model;
	}

	public void setColor(String color){
		this.color=color;
	}

	@Override
	public String toString(){
		return make+":"+model+":"+color;
	}
}