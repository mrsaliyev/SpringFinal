import LatestPosts from "./LatestPosts/LatestPosts";
// import BestPosts from "./BestPosts/BestPosts";

const Home = () => {
    return (
        <main className="home_main_content mt-3">
            <LatestPosts/>
            {/*<BestPosts/>*/}
        </main>
    )
}

export default Home