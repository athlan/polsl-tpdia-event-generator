var net = require('net');
var client = net.connect({
	port: 3005
},
function() { //'connect' listener
	console.log('client connected');
	
	client.write("ping: " + new Date());
});

client.on('data', function(data) {
	console.log(data.toString());
});
client.on('end', function() {
	console.log('client disconnected');
});
