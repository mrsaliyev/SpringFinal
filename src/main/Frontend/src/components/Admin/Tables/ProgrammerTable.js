import DataTable from "react-data-table-component";
import React, {useEffect, useState} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import DbService, {ADD, DELETE, TABLE_PROGRAMMERS} from "../../../_services/DbService";

export default function ProgrammerTable(props) {
    const columns = [
        {
            name: 'ID',
            selector: 'id',
            sortable: true,
        },
        {
            name: 'Email',
            selector: 'email',
            sortable: true,
        },
        {
            name: 'Name',
            selector: 'name',
            sortable: true,
        },
        {
            name: 'Surname',
            selector: 'surname',
            sortable: true,
        },
        {
            right: true,
            cell: (row) => <>
                <Button variant={'dark'} className={'mr-2'} onClick={() => {
                    // editItem(row.id)
                }}>EDIT</Button>
                <Button variant={'danger'} onClick={() => {
                    deleteItem(row.id)
                }}>DELETE</Button>
            </>
        }
    ]

    async function deleteItem(id) {
        DbService.changeItem(DELETE, TABLE_PROGRAMMERS, id)
            .then(_ => loadData())
            .catch(e => console.log(e))
    }

    const [show, setShow] = useState(false);
    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [img, setImg] = useState('');
    const [description, setDescription] = useState('');

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [data, setData] = useState([]);

    const loadData = () => {
        DbService.getAllByTable(TABLE_PROGRAMMERS).then(response => {
            setData((response.data).map(item => {
                return {
                    id: item.id,
                    email: item.email,
                    name: item.name,
                    surname: item.last_name,
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
    const onChangeLastName = event => {
        setLastName(event.target.value);
    }
    const onChangeEmail = event => {
        setEmail(event.target.value);
    }
    const onChangeImg = event => {
        setImg(event.target.value);
    }
    const onChangeDesc = event => {
        setDescription(event.target.value);
    }
    const onSubmitForm = event => {
        const data = {name, lastName, email, img, description}
        addItem(data)
            .then(_ => {
                setName('')
                setLastName('')
                setEmail('')
                setImg('')
                setDescription('')
            })
            .catch(e => console.log(e))
        event.preventDefault()
    }

    async function addItem(data) {
        DbService.changeItem(ADD, TABLE_PROGRAMMERS, data)
            .then(_ => loadData())
            .catch(e => console.log(e))
    }
    return (
        <>
            <Button variant={'dark'} size={'lg'} className="mx-3 my-2" onClick={handleShow}>ADD</Button>
            <DataTable
                columns={columns}
                data={data}
                title={'Programmers'}
                pagination
                paginationComponentOptions={props.paginationOptions}
            />

            <Modal show={show} animation={false} onHide={handleClose}>
                <Form onSubmit={onSubmitForm}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add to {TABLE_PROGRAMMERS}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form.Group>
                            <Form.Label>Name:</Form.Label>
                            <Form.Control
                                value={name}
                                onChange={onChangeName}
                                type="text"
                                placeholder="Enter name"
                                required
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Last Name:</Form.Label>
                            <Form.Control
                                value={lastName}
                                onChange={onChangeLastName}
                                type="text"
                                placeholder="Enter last name"
                                required
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Email:</Form.Label>
                            <Form.Control
                                value={email}
                                onChange={onChangeEmail}
                                type="email"
                                placeholder="Enter email"
                                required
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Image URL:</Form.Label>
                            <Form.Control
                                value={img}
                                onChange={onChangeImg}
                                type="text"
                                placeholder="Enter image URL"
                                required
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Description:</Form.Label>
                            <Form.Control
                                as={'textarea'}
                                rows={3}
                                value={description}
                                onChange={onChangeDesc}
                                type="text"
                                placeholder="Enter description"
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
