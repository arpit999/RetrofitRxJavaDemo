package com.example.retrofitrxjavademo.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieResopnse {

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<Movie> results;

	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<Movie> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}

}