import DataTable from "react-data-table-component";
import React, {useEffect, useState} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import DbService, {ADD, DELETE, TABLE_CATEGORIES} from "../../../_services/DbService";

export default function CategoryTable(props) {
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
        },
        {
            right: true,
            cell: (row) => <Button variant={'danger'} onClick={() => {
                deleteItem(row.id)
            }}>DELETE</Button>
        }
    ]

    async function deleteItem(id) {
        DbService.changeItem(DELETE, TABLE_CATEGORIES, id)
            .then(_ => loadData())
            .catch(e => console.log(e))
    }

    const [show, setShow] = useState(false);
    const [name, setName] = useState("");

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [data, setData] = useState([]);

    const loadData = () => {
        DbService.getAllByTable(TABLE_CATEGORIES).then(response => {
            setData((response.data).map(item => {
                return {
                    id: item.id,
                    name: item.category,
                }
            }))
        })
    }

    useEffect(() => {
        loadData()
    }, [])

    const onChangeName = event => {
        setName(event.target.value);
    }
    const onSubmitForm = event => {
        addItem(name)
            .then(_ => {
                setName("")
            })
            .catch(e => console.log(e))
        event.preventDefault()
    }

    async function addItem(data) {
        DbService.changeItem(ADD, TABLE_CATEGORIES, data)
            .then(_ => loadData())
            .catch(e => console.log(e))
    }

    return (
        <>
            <Button variant={'dark'} size={'lg'} className="mx-3 my-2" onClick={handleShow}>ADD</Button>
            <DataTable
                columns={columns}
                data={data}
                title={'Categories'}
                // pagination
                // paginationComponentOptions={props.paginationOptions}
            />

            <Modal show={show} animation={false} onHide={handleClose}>
                <Form onSubmit={onSubmitForm}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add to {TABLE_CATEGORIES}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Category name:</Form.Label>
                            <Form.Control
                                value={name}
                                onChange={onChangeName}
                                type="text"
                                placeholder="Enter name"
                                required
                            />
                        </Form.Group>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            CLOSE
                        </Button>
                        <Button variant="dark" type={'submit'}>
                            SAVE
                        </Button>
                    </Modal.Footer>
                </Form>
            </Modal>
        </>
    )
}
