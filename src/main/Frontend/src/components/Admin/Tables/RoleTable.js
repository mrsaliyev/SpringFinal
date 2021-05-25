import DataTable from "react-data-table-component";
import React from "react";
import {Button} from "react-bootstrap";

const columns = [
    {
        name: 'ID',
        selector: 'id',
        sortable: true,
    },
    {
        name: 'Name',
        selector: 'name',
        sortable: true,
    }
]

const data = [
    {
        id: 1,
        name: 'Admin'
    },
    {
        id: 2,
        name: 'User'
    },
]

const paginationOptions = {
    rowsPerPageText: 'Rows per page',
    rangeSeparatorText: 'of',
    selectAllRowsItem: true,
    selectAllRowsItemText: 'All'
}

export default function RoleTable() {
    return (
        <>
            <Button variant={'dark'} size={'lg'} className="mx-3 my-2">ADD</Button>
            <DataTable
                columns={columns}
                data={data}
                title={'Roles'}
                pagination
                paginationComponentOptions={paginationOptions}
            />
        </>
    )
}
