// import Experience from "./Experience/Experience";
// import Projects from "./Projects/Projects";
// import Skills from "./Skills/Skills";
// import About from "./About/About";
// import {useEffect, useState} from "react";
// import DbService, {TABLE_PROGRAMMERS} from "../../_services/DbService";

// export default function ProgrammerPage(props) {
//     const [programmer, setProgrammer] = useState({})

//     const loadData = () => {
//         DbService.getItemByIdAndTable(props.id, TABLE_PROGRAMMERS).then(response => {
//             setProgrammer(response.data);
//         })
//     }

//     useEffect( () => {
//         loadData();
//     }, [])

//     return (
//         <div className="container">
//             <div className="row mt-2">
//                 <div className="col-lg-3 col-xl-3 ">
//                     <About programmer={programmer}/>
//                     <Skills/>
//                 </div>
//                 <div className="col-lg-8 col-xl-8">
//                     <div className="card-box">
//                         <div className="tab-content">
//                             <div className="tab-pane show active" id="about-me">
//                                 <Projects/>
//                                 <br/>
//                                 <Experience/>
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     )
// }