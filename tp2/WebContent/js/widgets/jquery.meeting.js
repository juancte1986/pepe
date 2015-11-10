$.widget('custom.applyMeeting', {
	options : {
		urlContext : "",
		guestIds:"",
		guestsNames: {}

	},

	_create : function() {
		this._bindVars();
		this._initialize();
		this._bindEvents();
	},

	_bindVars : function() {
		this.urlContext = this.options.urlContext;
		this.guestIds = this.options.guestIds;
		this.guestsNames = this.options.guestsNames;
		this.date = this.element.find("#datepicker");
		this.inputGuest = this.element.find("#inputGuest");
		this.hiddenGuestsIds  = this.element.find("#hiddenGuestsIds");
		this.ulGuests = this.element.find("#ulGuests");
		this.hiddenGuestId = this.element.find("#hiddenGuestId");
	},

	_initialize : function() {
		this._createDatePicker();
		this._createAtocompletar();
		this._loadGuestIds();
		this._loadGuestNames();
	},

	_bindEvents : function() {
		this.element.find("#btn-addUser").click($.proxy(this._addUser, this));
		this.ulGuests.on('click', '.itemDelete', function() {
		    $(this).closest('li').remove();
		});
	},
	
	_addUser: function() {
		$('<li class="ui-state-default li-user" value="'+ this.hiddenGuestId.val() +'">' + this.inputGuest.val() + '<a class="itemDelete" href="#">Eliminar</a></li>').appendTo(this.ulGuests);
		this._loadhiddenGuestsIds();
	},
	
	_deleteUser: function () {
		this._loadhiddenGuestsIds();
	},
	
	_loadhiddenGuestsIds: function() {
		debugger;
		var string ="";
		var array = this.element.find(".li-user");
		for(var i = 0; i < array.length; i++){
			if(i == (array.length -1)){
				string+= array[i].value.toString();
			} else {
				string+= array[i].value.toString() + ",";
			}
		}
		this.hiddenGuestsIds.val(string);
	},
	
	_createDatePicker : function() {
		this.date.datepicker();
	},
	
	_createAtocompletar : function() {
		var url = this.urlContext + "/services/getUsers";
		this.inputGuest.autocomplete({
		 	minLength: 3,
			source : function(request, response) {
				$.ajax({
					url : url,
					type: "GET",
					data: { term: request.term },
					dataType : "json",
					success : function(data) {
						response($.map(data.users, function(item) {
							return {
								label : item.label,
								value : item.label,
								key : item.value
							};
						}));
					}
				});
			},
			select: $.proxy(this._loadHiddenGuestId ,this)
		});
	},
	
	_loadHiddenGuestId : function(event, ui) {
		debugger;
		this.hiddenGuestId.val(ui.item.key);
	},
	
	_loadGuestIds : function() {
		this.hiddenGuestsIds.val(this.guestsIds);
	},
	
	_loadGuestNames : function() {
		for(var i=0; i<this.guestsNames.length; i++){
			var guest = this.guestsNames[i].split(",");   
			$('<li class="li-user" value='+guest[1]+ '>' + guest[0] +' <a href="'+url+'">Eliminar</a></li>').appendTo(this.ulGuests);
		}
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
