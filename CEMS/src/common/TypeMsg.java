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
    }, DeleteRequest {
        public String toString() {
            return "Deleting request for time change";
        }
    }, DeleteRequestCompleted {
        public String toString() {
            return "request for time change was deleted";
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
            return "Time Request Declined";
        }
    },
    RequestIsDeclinedToLecturer {
        public String toString() {
            return "Time Request Declined";
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
    GetTestsByLecutrerForLecturerReport {
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
        public String toString() {
            return "User asked for the questions of the chosen test";
        }
    },
    GetTestQuestionsResponse {
        public String toString() {
            return "Server returned the list of test questions";
        }
    },
    GetActiveTests {
        public String toString() {
            return "User asked for the active tests table";
        }
    },
    GetActiveTestsResponse {
        public String toString() {
            return "Server returned the active tests table";
        }
    },
    GetCorrectAnswer {
        public String toString() {
            return "Get correct Answer From the data base";
        }
    },
    importedCorrectAnswer {
        public String toString() {
            return "Imported correct answer";
        }
    },
    UpdateRemainingTime {
        public String toString() {
            return "User wants to update the test's remaining time";
        }
    },
    UpdateRemainingTimeResponse {
        public String toString() {
            return "Server updated the test's remaining time";
        }
    },
    GetRemainingTime {
        public String toString() {
            return "User wants to get the test's remaining time";
        }
    },
    GetRemainingTimeResponse {
        public String toString() {
            return "Server returned the test's remaining time";
        }
    },
    getQuestionAndAnswerFromTest {
        public String toString() {
            return "Get Question and it's answers";
        }
    },
    importedQuestionAndAnswerFromTest {
        public String toString() {
            return "Imported Question and it's answers";
        }
    },
    GetTestQuestionsById {
        public String toString() {
            return "User asked for the questions of the chosen test";
        }
    },
    AddStudentAnswer {
        public String toString() {
            return "Student submitted an answer in the test";
        }
    },
    StudentAnswerAdded {
        public String toString() {
            return "Student final answer is saved";
        }
    },
    GetTestByID {
        public String toString() {
            return "Get Test information by it's id";
        }
    },
    ImportedTestByID {
        public String toString() {
            return "Test information imported";
        }
    },
    AddNewTestOfStudent {
        public String toString() {
            return "Student finished his test";
        }
    },
    TestOfStudentSaved {
        public String toString() {
            return "Test of student is saved";
        }
    },
    CheckStudentRegisteredCourse {
        public String toString() {
            return "Check if student is in this course";
        }
    },
    StudentVerified {
        public String toString() {
            return "Student id's is verified";
        }
    },
    IcreaseStudentsEnteringTest {
        public String toString() {
            return "Student is taking a test";
        }

    },
    TotalStudentsInTestIncreased {
        public String toString() {
            return "Student is taking a test";
        }

    },
    IcreaseStudentsFinishedTest {
        public String toString() {
            return "Student finished test";
        }

    },
    StudentsFinishedTestIncreased {
        public String toString() {
            return "Student is taking a test";
        }

    },
    changeTestDuration {
        public String toString() {
            return "Head Of Department want to change test duration";
        }

    },
    changeTestDurationAnswer{
        public String toString() {
            return "Change of test duration succeeded";
        }

    },
    TestDurationChanged {
        public String toString() {
            return "test's duration was changed";
        }

    },
    GetsubjectNametoID {
        public String toString() {
            return "get subject id";
        }

    },
    ImportedSubjectIDfromName {
        public String toString() {
            return "subject ID imported";
        }

    },
    RequestExtraTime {
        public String toString() {
            return "Lecturer requested extra time in test";
        }

    },
    ExtraTimeRequested {
        public String toString() {
            return "Lecturer requested extra time in test";
        }

    },
    TestDurationApprovedPopLecturer {
        public String toString() {
            return "Approved time change popup";
        }
    },
    TestDurationDeclinedPopLecturer {
        public String toString() {
            return "Declined time change popup";
        }
    },
    GetTestForApproval {
        public String toString() {
            return "User Asked All Test To Do Approve";
        }

    },
    GetTestForApprovalResponse {
        public String toString() {
            return "Server returned the list of tests needed approval";
        }
    },
    UpdateTheApproveofLecturer {
        public String toString() {
            return "User asked to update the approve";
        }
    },
    UpdateTheApproveofLecturerResponse {
        public String toString() {
            return "Server update your approval";
        }
    },

    AddNewActiveTest {
        public String toString() {
            return "Lecturer wants to activate a test";
        }
    },
    AddNewActiveTestResponse {
        public String toString() {
            return "Server activated the test";
        }
    },
    AddNewAfterTestInfo {
        public String toString() {
            return "Lecturer wants to add initial information a test";
        }
    },
    AddNewAfterTestInfoResponse {
        public String toString() {
            return "Server added initial test information";
        }
    },
    NumberOfAttendedCounter {
        public String toString() {
            return "Request to get number of students who entered a test";
        }

    },
    ImportedNumberOfAttendedCounter {
        public String toString() {
            return "imported number of students who entered a test";
        }

    },
    CountRegisteredStudents {
        public String toString() {
            return "Request to get number of students who are assigned to a test (the course)";
        }

    },
    ImportedRegisteredStudents {
        public String toString() {
            return "Imported number of students who are registered to the test";
        }

    },
    FinishAfterTestInfo {
        public String toString() {
            return "Test is over, need to save afterTestInfo";
        }

    },
    AfterTestRowCompleted {
        public String toString() {
            return "After test info saved";
        }

    },
    UnActivateTest {
        public String toString() {
            return "Test is over, remove from activetest";
        }

    },
    CompleteUnactivatingTest {
        public String toString() {
            return "Test is no longer active";
        }

    },
    CountNumberOfFinished {
        public String toString() {
            return "Count number of students who submitted the test";
        }

    },
    ImportedNumberOfFinished {
        public String toString() {
            return "Imported number of students who submitted the test";
        }

    },
    DetectedCheating{
        public String toString() {
            return "User Asked to update student and test have cheating";
        }
    },
    DetectedCheatingResponse{
        public String toString() {
            return "the test have cheating updating";
        }

    },
}






