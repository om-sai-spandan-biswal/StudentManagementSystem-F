export default function ErrorBox({ error }) {
    if (!error) return null;

    return (
        <div className="bg-red-100 text-red-700 p-2 rounded mb-2">
            {error}
        </div>
    );
}