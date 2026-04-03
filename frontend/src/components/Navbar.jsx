import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav className="bg-gray-900 text-white px-6 py-3 flex gap-6 shadow">
            <Link to="/">Students</Link>
            <Link to="/courses">Courses</Link>
            <Link to="/enrollments">Enroll</Link>
            <Link to="/bulk">Bulk Upload</Link>
        </nav>
    );
}