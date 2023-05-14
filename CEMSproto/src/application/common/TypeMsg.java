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
//	},
//	ImportedSuccessfully {
//		public String toString() {
//			return "User asks to logout";
//		}
	},GetQuestions {
		public String toString() {
			return "User asked table questions";
		}
	},QuestionsResponse {
		public String toString() {
			return "Here is the questions table";
		}
	},EditQuestion {
		public String toString() {
			return "User asked to edit question";
		}
	},QuestionUpdated {
	public String toString() {
		return "User updated a question ";
	}
	}


}


