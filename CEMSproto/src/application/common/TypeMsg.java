package application.common;

public enum TypeMsg {
	
	
	Disconnected {
		public String toString() {
			return "Disconnected";
		}
	},
	Connected {
		public String toString() {
			return "Connected";
		}
	},
	ImportedSuccessfully {
		public String toString() {
			return "User asks to logout";
		}
	}
}


