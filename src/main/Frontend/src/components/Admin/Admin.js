import React from 'react'
import $ from 'jquery'
import {Route, Switch} from "react-router-dom";
import AdminHeader from "./AdminHeader";
import AdminSidebar from "./AdminSidebar";
import UserTable from "./Tables/UserTable";
import TechnologyTable from "./Tables/TechnologyTable";
import CategoryTable from "./Tables/CategoryTable";
import RoleTable from "./Tables/RoleTable";
import SkillTable from "./Tables/SkillTable";
import ProgrammerTable from "./Tables/ProgrammerTable";
import StatusTable from "./Tables/StatusTable";
import ProjectTable from "./Tables/ProjectTable";
import DataTable from "react-data-table-component";

const paginationOptions = {
    rowsPerPageText: '',
    rangeSeparatorText: 'of',
    selectAllRowsItem: true,
    selectAllRowsItemText: 'All'
}

class Admin extends React.Component {
    componentDidMount() {
        $(document).ready(function () {
            $("#open-menu").click(function () {
                if ($('#page-container').hasClass('show-menu')) {
                    $("#page-container").removeClass('show-menu');
                } else {
                    $("#page-container").addClass('show-menu');
                }
            });
        });
    }

    render() {
        return (
            <div id="page-container" className="main-admin show-menu">
                <AdminHeader/>
               <AdminSidebar/> 

                 <div className="main-body-content w-100 ets-pt">
                    <div className="table-responsive bg-light">
                        <Switch>
                            <Route path={'/admin/users'}
                                   render={() => <UserTable paginationOptions={paginationOptions}/>}/>
                            <Route path={'/admin/categories'}
                                   render={() => <CategoryTable paginationOptions={paginationOptions}/>}/>
                            <Route path={'/admin/programmers'}
                                   render={() => <ProgrammerTable paginationOptions={paginationOptions}/>}/>
                            <Route path={'/admin/projects'}
                                   render={() => <ProjectTable paginationOptions={paginationOptions}/>}/>
                            <Route path={'/admin/roles'}
                                   render={() => <RoleTable paginationOptions={paginationOptions}/>}/>
                            <Route path={'/admin/skills'}
                                   render={() => <SkillTable paginationOptions={paginationOptions}/>}/>
                            <Route path={'/admin/statuses'}
                                   render={() => <StatusTable paginationOptions={paginationOptions}/>}/>
                            <Route path={'/admin/technologies'}
                                   render={() => <TechnologyTable paginationOptions={paginationOptions}/>}/>
                        </Switch>
                    </div>
                </div> 
            </div>
        )
    }
}
export default Admin
