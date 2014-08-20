var deferred = require('deferred')

var loader = require('./Loader/JavaProcLoader.js')

exports.getModel = function(sourceFile) {
	var def = deferred();
	
	var resultPromise = loader.loadData(sourceFile)
	
	resultPromise(
		function(data) {
			var numbers = data.split("\n")
			
			numbers = numbers.map(function(row) {
				return row.replace(/\s+/, "")
			}).filter(function(row) {
				return row != ""
			}).map(function(row) {
				return parseInt(row, 10)
			})
			
			numbers.shift() // remove first value
			
			def.resolve(numbers)
		},
		function(err) {
			def.reject(err)
		}
	)
	
	return def.promise
}
