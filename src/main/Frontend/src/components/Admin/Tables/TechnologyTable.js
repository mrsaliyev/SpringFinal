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
        name: 'Java'
    },
    {
        id: 2,
        name: 'Spring'
    },
    {
        id: 3,
        name: 'React'
    },
]

export default function TechnologyTable(props) {
    return (
        <>
            <Button variant={'dark'} size={'lg'} className="mx-3 my-2">ADD</Button>
            <DataTable
                columns={columns}
                data={data}
                title={'Technologies'}
                pagination
                paginationComponentOptions={props.paginationOptions}
            />
        </>
    )
}
