import React, { Component } from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import toDoApp from './reducers';
import Col from 'react-bootstrap/Col';
import Container  from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';

const store = createStore(toDoApp);

class App extends Component {
  render() {
    return (
      <Container>
        <Row className="row">
          <Col xs={12}>
            <h1>My New React Bootstrap SPA</h1>   {/* 원하는 내용 */}
            <Button>Look, I'm a button!</Button>  {/* 원하는 내용 */} 
          </Col>
        </Row>
      </Container>
    );
  }
}

export default App;