import Post from "../Post/Post";
import {useEffect, useState} from "react";
import DbService, {TABLE_PROJECTS} from "../../../_services/DbService";

function LatestBooks({searchName}) {
    const [data, setData] = useState([]);
    const [statusCard,setStatusCard] = useState(false);

    console.log(data);



    useEffect(() => {
        console.log(searchName);
        searchByName(searchName);
        if(searchName == null){
            setStatusCard(false);
            searchName = "War and peace";
        //  loadData();
        //   profile();
        }
      
      },[searchName])

    async function searchByName(name){
        let jwt = localStorage.getItem('jwtToken');
        console.log(name);
        if(name.length == 0){
            name = null;
            console.log(name);
          }
        // if(jwt != null){
            let response = await fetch("http://localhost:8080/searchProjects/"+name, {
                headers: {
                  "Content-Type": "application/json",
                //   "Authorization":"Bearer " +  jwt,
                }
              });
              
              if(response != 'empty'){
                  let tableData = await response.json();
                  setStatusCard(true);
                  setData(tableData);
                  if(name  == null){
                  setStatusCard(false);
              
                  }
              }
        }  
    
    
    return  <div>
        <div className="row">
        {data?.map(row=>(
            <div className="col-8" >
        <div class="card mb-4" style={{marginLeft : '100px'}}>
  <div class="row g-0">
    <div class="col-md-4">
      <img src={row.imgPath} style={{height : '300px'}}  alt="..."/>
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">{row.title}</h5>
        <p class="card-text">{row.body}</p>
        <p class="card-text"><small class="text-muted">{row.createdAt}</small></p>
      </div>
    </div>
  </div>
</div>
</div>
        ))}
    </div>
    </div>
}


    

            




function LatestPosts()  {
    const [posts, setPosts] = useState([])
    const[searchName,setSearchName] = useState("")
    const handleSearchNameChange = event => {
        setSearchName(event.target.value);
    }
    
    useEffect(() => {
        DbService.getAllByTable(TABLE_PROJECTS)
            .then(r => {
                setPosts(r.data)
            })
    }, [])

    console.log(searchName);

    return (

        <section className="home_latest_post">
              <nav>
    <div class="nav-wrapper">
      <form>
        <div className="form-group">
            <h3>   Search book:</h3>
            <input id="search" class="form-control" type="search" value={searchName} onChange={handleSearchNameChange} style={{marginLeft : '30px',marginBottom : '50px', width : '200px'}}/>
        </div>
      </form>
    </div>
  </nav>
  <LatestBooks searchName = {searchName} /> 
            <h2>Books</h2>
            <div className="container">
                <div className="row mt-5">

                    {posts.map(p => {
                        return <Post key={p.id}
                                     id={p.id}
                                     img={p.imgPath}
                                     title={p.title}
                                     shortDescription={p.shortDescription}
                                     body={p.body}
                                     date={p.createdAt}
                                     categories={p.category}
                        />
                    })}

                </div>
            </div>
        </section>
    )
}


export default LatestPosts
