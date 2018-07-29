package com.pluralsight.model;

import javax.annotation.Generated;

public class Ride {

	public Ride() {
		super();
	}

	private int duration;
	private Integer id;
	private String name;

	@Generated("SparkTools")
	private Ride(Builder builder) {
		this.duration = builder.duration;
		this.id = builder.id;
		this.name = builder.name;
	}

	public int getDuration() {
		return duration;
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", name=" + name + ", duration=" + duration + "]";
	}

	/**
	 * Creates builder to build {@link Ride}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Ride}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private int duration;
		private Integer id;
		private String name;

		private Builder() {
		}

		public Builder withDuration(int duration) {
			this.duration = duration;
			return this;
		}

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Ride build() {
			return new Ride(this);
		}
	}

}
