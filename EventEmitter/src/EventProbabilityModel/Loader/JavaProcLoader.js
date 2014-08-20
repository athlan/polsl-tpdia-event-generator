var process = require('child_process')
var deferred = require('deferred')

exports.loadData = function(sourceFile) {
	var def = deferred();
	var proc = process.spawn('java', ['-jar', '../bin/DumpPlotDataFromBmp.jar', sourceFile])

	var commandProcessBuff = '';
	
	proc.stdout.on('data', function (data) {
		commandProcessBuff += data
	})

	proc.stderr.on('data', function (data) {
		def.reject(new Error("An error with executing command occured: " + data))
	})

	proc.on('close', function (code) {
		def.resolve(commandProcessBuff)
	})
	
	return def.promise
}
