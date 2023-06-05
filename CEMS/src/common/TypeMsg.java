package common;

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
    }, GetQuestionsBySubject {
        public String toString() {
            return "User asked for table questions of specific Subject";
        }
    }, QuestionsBySubjectImported {
        public String toString() {
            return "imported all questions of specific subject";
        }
    }, GetAllQuestions {
        public String toString() {
            return "User asked for all questions";
        }
    }, allQuestionImported {
        public String toString() {
            return "imported all questions successfully ";
        }
    }, TryLogin {
        public String toString() {
            return "new user try to login.";
        }
    }, LoginResponse {
        public String toString() {
            return "User pressed Login";
        }

    }, importSubjects {
        public String toString() {
            return "import Subjects list for user";
        }
    }, CoursesimportSuccess {
        public String toString() {
            return "import courses Successfully";
        }
    }, importCourses {
        public String toString() {
            return "import Courses list for user";
        }

    }, EditQuestion {
        public String toString() {
            return "User asked to edit question";
        }
    }, AddNewQuestion {
        public String toString() {
            return "User asked to add a question";
        }
    }, QuestionAddedSuccessfuly {
        public String toString() {
            return "User added a question";
        }

    }, DeleteQuestion {
        public String toString() {
            return "User asked to delete question";
        }
    }, QuestionDeleted {
        public String toString() {
            return "User deleted a question";
        }
    }, QuestionUpdated {
        public String toString() {
            return "User updated a question ";
        }

    }, SubjectsimportSuccess {
        public String toString() {
            return "import subjects Successfully";
        }
    }, GetCourseTable {
        public String toString() {
            return "User Asked The Courses";
        }
    }, CourseTableResponse {
        public String toString() {
            return "Here is the course table";
        }
    }, GetAllTestsTable {
        public String toString() {
            return "User requested the table of every test in the DB";
        }
    }, GetAllTestsTableResponse {
        public String toString() {
            return "Server returned the table of every test in the DB";
        }
    }, GetTestsBySubject {
        public String toString() {
            return "User requested the tests table filtered by subject";
        }
    }, GetTestsBySubjectResponse {
        public String toString() {
            return "Server returned the table of tests filtered by subject";
        }
    }, TestTableResponse {
        public String toString() {
            return "Here Is The Table Test";
        }
    }, AddNewTestQuestion {
        public String toString() {
            return "Lecturer asked to save the questions of test";
        }
    }, AddNewTestQuestionsResponse {
        public String toString() {
            return "The Questions is s saved";
        }
    }, AddNewTest {
        public String toString() {
            return "Lecturer asked to save test";
        }
    }, AddNewTestResponse {
        public String toString() {
            return "The test is saved";
        }
    },
    GetRequestsBySubject {
        public String toString() {
            return "Head Of Department want to import table of requests";
        }
    },
    RequestImportedSuccessfully {
        public String toString() {
            return "Requests table imported successfully";
        }
    },
    ApproveRequestByHeadOfDepartment {
        public String toString() {
            return "Head Of Department want to approve a requests";
        }
    },
    RequestIsApproved {
        public String toString() {
            return "The request has been approved";
        }
    },

    DeclineRequestByHeadOfDepartment {
        public String toString() {
            return "Head Of Department want to decline a requests";
        }
    },
    RequestIsDeclined {
        public String toString() {
            return "The request has declined";
        }
    },
    GetStudentReport {
        public String toString() {
            return "Head of department want to get a report of a student";
        }
    },
    StudentReportImported {
        public String toString() {
            return "Student's report was imported";
        }
    },
    GetUser {
        public String toString() {
            return "Get list of users";
        }
    },
    UserImported {
        public String toString() {
            return "Imported list of users";
        }
    },
    GetTestsByLecutrer {
        public String toString() {
            return "Get list tests made by lecutrer";
        }
    },
    ImportedTestsByLecturer {
        public String toString() {
            return "Imported list of test made by lecturer";
        }
    },
    GetTestsByCourse {
        public String toString() {
            return "Get list tests made by course";
        }
    },
    ImportedTestsByCourse {
        public String toString() {
            return "Imported list of tests by course";
        }
    },
    GetTestsByLecutrerForLecturerReport{
        public String toString() {
            return "Imported list of tests by lecturer";
        }
    },
    ImportedTestsByLecturerForLecturerReport {
        public String toString() {
            return "Imported list of test made by lecturer";
        }
    },
    DeleteTest {
        public String toString() {
            return "User asked to delete a test";
        }
    },
    DeleteTestResponse {
        public String toString() {
            return "Server deleted the test";
        }
    },
    GetTestQuestions {
        public String toString() { return "User asked for the questions of the chosen test";}
    },
    GetTestQuestionsResponse {
        public String toString() { return "Server returned the list of test questions"; }
    },
    GetActiveTests {
        public String toString() { return "User asked for the active tests table"; }
    },
    GetActiveTestsResponse {
        public String toString() { return "Server returned the active tests table"; }
    },
    GetCorrectAnswer {
        public String toString() { return "Get correct Answer From the data base"; }
    },
    importedCorrectAnswer {
        public String toString() { return "Imported correct answer"; }
    },
    UpdateRemainingTime {
        public String toString() { return "User wants to update the test's remaining time"; }
    },
    UpdateRemainingTimeResponse {
        public String toString() { return "Server updated the test's remaining time"; }
    },
    GetRemainingTime {
        public String toString() { return "User wants to get the test's remaining time"; }
    },
    GetRemainingTimeResponse {
        public String toString() { return "Server returned the test's remaining time"; }
    }


}





