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
        name: 'In progress'
    },
    {
        id: 2,
        name: 'Done'
    },
]

export default function StatusTable(props) {
    return (
        <>
            <Button variant={'dark'} size={'lg'} className="mx-3 my-2">ADD</Button>
            <DataTable
                columns={columns}
                data={data}
                title={'Statuses'}
                pagination
                paginationComponentOptions={props.paginationOptions}
            />
        </>
    )
}
