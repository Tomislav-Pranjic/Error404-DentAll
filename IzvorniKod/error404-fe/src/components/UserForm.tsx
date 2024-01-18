function UserForm() {

    function onChange(event) {
        const {name, value} = event.target;
        setForm(oldForm => ({...oldForm, [name]: value}))
    }

    function onSubmit(e) {
        e.preventDefault();
        const data = {
            id: form.id,
            firstname: form.firstname,
            lastname: form.lastname,
        };
        const options = {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/students', options);
    }

    function isValid() {
        
    }
    
    return (
        <div className="UserForm">
            <h2>New User</h2>
            <form onSubmit={onSubmit}>
                <div className="FormRow">
                    <label>ID</label>
                    <input name='id' onChange={onChange} value={form.id}/>
                </div>
                <div className="FormRow">
                    <label>First name</label>
                    <input name='firstname' onChange={onChange} value={form.firstname}/>
                </div>
                <div className="FormRow">
                    <label>Last name</label>
                    <input name='lastname' onChange={onChange} value={form.lastname}/>
                </div>
                <button type="submit">Add User</button>
            </form>
        </div>
    )
}
