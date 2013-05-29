package org.Algy.frames;

import org.Algy.dialogs.RenamerDialog.RenamingType;

public class EditorContext {
	@Override
	public String toString() {
		return "EditorContext [type=" + type + ", typeSure=" + typeSure
				+ ", literal=" + literal + "]";
	}
	public RenamingType type ;
	public boolean typeSure = false ;
	public String literal = "";
}
