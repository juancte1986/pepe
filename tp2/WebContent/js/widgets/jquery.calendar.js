$.widget('custom.applyCalendar', {
	options : {
		urlContext : "",
	},

	_create : function() {
		this._bindVars();
		this._initialize();
		this._bindEvents();
	},

	_bindVars : function() {
	},

	_initialize : function() {
	},

	_bindEvents : function() {
	},

	destroy : function() {
		for ( var name in this.calls) {
			this.calls[name].abort();
			this.calls[name] = null;
		}

		if (typeof (CollectGarbage) == "function") {
			CollectGarbage();
		}
	}

});
