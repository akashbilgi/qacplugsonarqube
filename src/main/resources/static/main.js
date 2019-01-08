define(['dom', 'tabsFactory', 'tabUpdate', 'tabExport', 'tabImport'], function(dom, tabsFactory, tabUpdate, tabExport, tabImport) {
	return {
		main: function(options) {
			var header = dom.createElement(options.el, 'header', { className: 'page-header'});
			dom.createElement(header, 'h1', { className: 'page-title', textContent: 'QAC plugin CHECK'});
			dom.createElement(header, 'div', { className: 'page-description', textContent: 'Allows you to merge QAC with SonarQube dashboard'});
			
			var tabs = tabsFactory.create(options.el);
			//tabs.tab('QAC', tabUpdate.create(options.component.key));
			tabs.tab('Run QAC', tabExport.create(options.component.key));
			//tabs.tab('Import', tabImport.create(options.component.key));
			tabs.show('Update');
		}
	}
});
