package org.techhub.com.Service;

import java.nio.file.WatchService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techhub.com.Model.MovieInfo;
import org.techhub.com.Model.WatchList;
import org.techhub.com.Repository.WatchListRepoImpl;

@Service("watchService")
public class WatchServiceImpl implements WatchListService{
	
	@Autowired
	WatchListRepoImpl watchRepo;
	@Override
	public boolean addWatchList(WatchList watch) {

		return watchRepo.addWatchList(watch);
	}
	@Override
	public List<MovieInfo> getUserWatchList(int user_id) {
		// TODO Auto-generated method stub
		return watchRepo.getUserWatchList(user_id);
	}
	
	
}
