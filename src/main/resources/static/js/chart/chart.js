(function(){
var x_labels = {1: "pre", 2: "base", 3: "detail", 4: "dev", 5: "deploy"};
var color_map = {org1:'#00ff00', org2:'#0000ff'};
var chart = c3.generate({
	size: {
		height: 300,
		width: 800
	},
	point: {
		r: 5
	},
    axis: {
        x: {
            label: 'step',
            tick: {
            	format: function(d){
            		var result = x_labels[d];
            		if(result === undefined) return d;
            		return result;
            	},
                values: [1,2,3,4,5]
            },
        	padding: {
        		left: 1,
        		right: 1,
        	},
            max: 5,
            min: 1
        },
        y: {
            label: 'priority',
            tick: {
            	values: [1,2,3,4,5]
            },
            inverted: true,
            max: 5,
            min: 1
        }
    },
    tooltip: {
    	contents: function(d, defaultTitleFormat, defaultValueFormat, color){
    		var $$ = this;
    		var data = json_data[d[0].index];
    		var title = defaultTitleFormat(data.request.title);
    		var text = "<table class=" + $$.CLASS.tooltip +">" + "<tr><th colspan='2'>" + title + "</th></tr>";
    		text = text + "<tr><td class='name'>period</td><td class='value'>" + data.period +"</td></tr>";
    		text = text + "<tr><td class='name'>voted by</td><td class='value'>" + data.organization.name +"</td></tr>";
    		text = text + "</table>";
    		return text
    	}
    },
    grid: {
    	x: {
    		show: true
    	},
    	y: {
    		show: true
    	}
    },
    data: {
    	labels: true,
        xs: {
            priority: 'step',
        },
        type: 'scatter',
        json: {},
        color: function (color, d) {
        	if(typeof d === 'object'){
        		var data = json_data[d.index];
        		var result = color_map[data.organization.name];
        		if (result === undefined) return color;
        		return result;
        	} else {
        		return color;
        	}
        }
    },
    legend: {
    	hide: true
    }
});
var json_data = {};
var xhr = new XMLHttpRequest();
xhr.open("GET", "http://localhost:8082/votes", true);
xhr.onload = function (e) {
  if (xhr.readyState === 4) {
    if (xhr.status === 200) {
      json_data = JSON.parse(xhr.responseText)._embedded.votes;
      chart.load({
        json: json_data,
        keys: {
        	value: ['priority', 'step']
        }
      });
    }
  }
};
xhr.send(null);
}()
)