package org.techhub.com.Repository;

import org.techhub.com.Model.MovieInfo;
import org.techhub.com.Model.WatchList;
import java.util.*;

public interface WatchListRepository {
	
	public boolean addWatchList(WatchList  watch);
	
	public List<MovieInfo> getUserWatchList(int user_id);
}
