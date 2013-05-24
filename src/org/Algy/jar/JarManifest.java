package org.Algy.jar;

import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;


public class JarManifest extends JarObject {
	public JarManifest(Manifest manifest) {
		super();
		this.manifest = manifest;
	}
	Manifest manifest;

	public Attributes getAttributes(String name) {
		return manifest.getAttributes(name);
	}

	public Map<String, Attributes> getEntries() {
		return manifest.getEntries();
	}

	public Attributes getMainAttributes() {
		return manifest.getMainAttributes();
	}
}
