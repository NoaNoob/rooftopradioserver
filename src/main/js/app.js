'use strict';

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
			this.setState({bands: response.entity._embedded.bands});
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
			<Band key={band._links.self.href} band={band}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Name</th>
						<th>Location</th>
						<th>Type</th>
						<th>Genre</th>
						<th>Internet</th>
					</tr>
					{bands}
				</tbody>
			</table>
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
				<td>{this.props.band.internet}</td>
			</tr>
		)
	}
}

ReactDOM.render(
		<App />,
		document.getElementById('react')
	)