var async = require('async')
var events = require('events')

var eventProbabilityModel = require('./src/EventProbabilityModel/Model.js')
var eventEmitter = require('./src/EventEmitter.js')

var net = require('net')

var server
var eventbus = new events.EventEmitter()

var eventOccurenceCallback = function() {
	eventbus.emit('eventOccurence')
	console.log('event occured!')
}

async.waterfall([
	// loading model
	function(callback) {
		var resultPromise = eventProbabilityModel.getModel('../DumpPlotDataFromBmp/wykres.bmp')
		
		resultPromise(
			function(model) {
				callback(null, model)
			},
			function(err) {
				callback(err)
			}
		)
	},
	
	function(model, callback) {
		var server = net.createServer(function(c) {
		
			var onEventOccuredCallback = function() {
				c.write('new event!!\r\n');
			}
			
			console.log('server connected');
			eventbus.on('eventOccurence', onEventOccuredCallback)
			
			c.on('end', function() {
				eventbus.removeListener('eventOccurence', onEventOccuredCallback)
				console.log('server disconnected');
			})
			
			c.on('error', function() {
				eventbus.removeListener('eventOccurence', onEventOccuredCallback)
				console.log('server error');
			})
			
			c.on('data', function(data) {
				console.log(data.toString());
			});
			
			c.pipe(c);
		});

		// listen server
		server.listen(3005, function() {
			console.log('server bound');
			
			callback(null, model)
		});
	},
	
	// processing events
	function(model, callback) {
		
		eventEmitter.emitEventsUsingModel(model, eventOccurenceCallback)
	}
], function (err, result) {
})
