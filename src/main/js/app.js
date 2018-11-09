'use strict';

import { ThemeProvider, createTheme, Arwes, Words, Link } from 'arwes';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {bands: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/bands'}).done(response => {
			this.setState({bands: response.entity});
		});
	}

	render() {
		return (
			<BandList bands={this.state.bands}/>
		)
	}
}

class BandList extends React.Component{
	render() {
		const bands = this.props.bands.map(band =>
			<Band key={band.internet} band={band}/>
		);
		return (
		<div>
			<table class="table table-dark">
				<tbody>
				  <thead>
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Location</th>
						<th scope="col">Type</th>
						<th scope="col">Genre</th>
						<th scope="col">Internet</th>
					</tr>
				  </thead>
				  <tbody>
					{bands}
				  </tbody>
				</tbody>
			</table>
		</div>
		)
	}
}

class Band extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.band.name}</td>
				<td>{this.props.band.location}</td>
				<td>{this.props.band.type}</td>
				<td>{this.props.band.genre}</td>
				<td><a href={this.props.band.internet} target="_blank">{this.props.band.internet}</a></td>
			</tr>
		)
	}
}

ReactDOM.render(
		<App />,
		document.getElementById('react')
	)