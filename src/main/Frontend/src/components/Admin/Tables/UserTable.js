import DataTable from "react-data-table-component";
import React, {useEffect, useState} from "react";
import DbService, {TABLE_USERS} from "../../../_services/DbService";

const columns = [
    {
        name: 'ID',
        selector: 'id',
        sortable: true,
    },
    {
        name: 'Username',
        selector: 'username',
        sortable: true,
    },
    {
        name: 'Roles',
        selector: 'roles',
        sortable: true,
    },
]

export default function UserTable(props) {
    const [data, setData] = useState([]);

    const loadData = () => {
        DbService.getAllByTable(TABLE_USERS).then(response => {
            setData((response.data).map(item => {
                return {
                    id: item.id,
                    username: item.username,
                    roles: (item.roles).map(i => i.authority + ' ')
                }
            }))
        })
    }

    useEffect(() => {
        loadData()
    }, [])

    return (
        <DataTable
            columns={columns}
            data={data}
            title={'Users'}
            pagination
            paginationComponentOptions={props.paginationOptions}
        />
    )
}
