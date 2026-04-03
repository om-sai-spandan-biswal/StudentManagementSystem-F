import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import ToastProvider from "./context/ToastProvider.jsx";

import Students from "./pages/Students";
import StudentForm from "./pages/StudentForm";
import StudentDetails from "./pages/StudentDetails";

import Courses from "./pages/Courses";
import CourseForm from "./pages/CourseForm";
import CourseDetails from "./pages/CourseDetails";

import Enrollments from "./pages/Enrollments";
import BulkUpload from "./pages/BulkUpload";

export default function App() {
  return (
      <ToastProvider>
        <BrowserRouter>
          <Navbar />

          <div className="p-4">
            <Routes>
              <Route path="/" element={<Students />} />
              <Route path="/student/new" element={<StudentForm />} />
              <Route path="/student/edit/:id" element={<StudentForm />} />
              <Route path="/student/:id" element={<StudentDetails />} />

              <Route path="/courses" element={<Courses />} />
              <Route path="/course/new" element={<CourseForm />} />
              <Route path="/course/edit/:id" element={<CourseForm />} />
              <Route path="/course/:id" element={<CourseDetails />} />

              <Route path="/enrollments" element={<Enrollments />} />
              <Route path="/bulk" element={<BulkUpload />} />
            </Routes>
          </div>
        </BrowserRouter>
      </ToastProvider>
  );
}