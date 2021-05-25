import CardAuthor from "./CardAuthor";

export default function Authors() {
    return (
        <div className="card mb-3 mt-2">
            <h5 className="card-header">Developed by</h5>
            <div className="card-body">
                <div className="row">
                    <CardAuthor/>
                    <CardAuthor/>
                    <CardAuthor/>
                </div>
            </div>
        </div>
    )
}