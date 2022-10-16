package model;

import java.util.List;

public class Model {
	
	private List<Module> modules;
	
	public Model() {
	}
	
	public void init() {
		for(Module module : this.modules) {
			module.init();
		}
	}
	
	public Module getModuleByName(String name) {
		for(Module module : this.modules) {
			if(module.getName().equals(name)) {
				return module;
			}
		}
		return null;
	}
	
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
}
