package org.techhub.com.Service;

import java.util.List;

import org.techhub.com.Model.MovieInfo;
import org.techhub.com.Model.WatchList;

public interface WatchListService {
	
	public boolean addWatchList(WatchList  watch);
	
	public List<MovieInfo> getUserWatchList(int user_id);


}
