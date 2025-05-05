package org.techhub.com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchList {
	private int watchlist_id;
	private int user_id;
	private int movie_id;
	private String added_at;
}
