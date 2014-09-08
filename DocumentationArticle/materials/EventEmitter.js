exports.emitEventsUsingModel = function(model, eventOccurenceCallback) {
	var interval = (86400 / model.length) * 1000
	var i = 0
	var eventId = 0
	var intervalObject
	
	var intervalCallback = function() {
		clearInterval(intervalObject)
		var numberOfEvents = model[i]
		
		if(numberOfEvents > 0) {
			var eventInterval = Math.floor(interval / numberOfEvents)
			
			intervalObject = setInterval(function() {
				++eventId
				eventOccurenceCallback()
			}, eventInterval)
		}
		
		++i
		
		if(i >= model.length)
			i = 0
	}
	
	setInterval(intervalCallback, interval)
	intervalCallback()
}