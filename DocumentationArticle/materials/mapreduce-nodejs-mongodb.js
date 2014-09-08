var map_comments_and_mentions = function() {
	var date = this.statDate - this.statDate % (60 * 60 * 24)
	
	var key = {
		statDate: date,
		pageId: this.pageId
	}
	var result = { mentions: 0, retweets: 0, comments: 0 }
	
	var reply_id = this.in_reply_to_status || ""
	if (reply_id.length) {
		result.comments++
	} else {
		result.mentions++
	}
	
	emit(key, result);
};

var map_retweets = function() {
	var date = this.statDate - this.statDate % (60 * 60 * 24)
	
	var key = {
		statDate: date,
		pageId: this.pageId
	}
	
	emit(key, {mentions: 0, retweets: 1, comments: 0})
};


var reduce = function(key, values) {
	var result = {mentions: 0, retweets: 0, comments: 0}

	values.forEach(function(value) {
		result.mentions += (value.mentions !== null) ? value.mentions : 0
		result.comments += (value.comments !== null) ? value.comments : 0
		result.comments += (value.comments !== null) ? value.comments : 0
	})
	
	return result
};

// replace on first reduce to flush the temporary collection
table_mentions.mapReduce(map_comments_and_mentions, reduce, {out: {replace: "twitter_interactions_stats" }}, (err, results) {
	console.log('Ready!')
});

// replace on first reduce to flush the temporary collection
table_mentions.mapReduce(map_retweets, reduce, {out: {replace: "twitter_interactions_stats" }}, (err, results) {
	console.log('Ready!')
});
