/*
    jQuery UI Autocomplete AutoFill extension 
    automatically fills in the input box when only one suggestion is to be shown
*/

;(function($) {
    $.extend($.ui.autocomplete.options, {autoFill: true});

    $.extend($.ui.autocomplete.prototype, {
        _response_original: $.ui.autocomplete.prototype._response,
        _response: function(content) {
        	debugger;
            if (this.options.autoFill && content.length == 1) {
                content = this._normalize(content);
                this.element.val(content[0].value);
                this.close();
                this.element.removeClass("ui-autocomplete-loading");
                return;
            }

            this._response_original(content);
        }
    });
})(jQuery);